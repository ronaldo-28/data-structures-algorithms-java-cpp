class AllOne {

    friend class CValueBin;
    class CValueBin;

    class CItem {
        public:

        // Where we are in the lists.
        CValueBin   *m_pCurrentBin = nullptr;
        CItem   *m_pPrev = nullptr;
        CItem   *m_pNext = nullptr;

        // What we are.
        string  m_Key;
        int     m_Value = 0;

        public:
        CItem( string key, int value ) {
            m_Key = key;
            m_Value = value;
        }
    };

    class CValueBin {
    private:
        friend class AllOne;

        AllOne      *m_pParent = nullptr;
        CValueBin   *m_pPrevBin = nullptr;
        CValueBin   *m_pNextBin = nullptr;

        CItem   *m_pHead = nullptr;
        int     m_BinValue = 0;

    protected:
        inline bool OnlyItemInBin( const CItem *pItem ) const 
        {
            return (pItem == m_pHead) && (pItem->m_pNext == nullptr);
        }

    public:
        CValueBin( int value, AllOne *pParent ) {
            m_BinValue = value;
            m_pParent = pParent;
        }
    
        bool        AddItemToBin( CItem *pItem ) {
            if( !pItem ) {
                return false;
            }

            assert( pItem->m_Value == m_BinValue );
            pItem->m_pCurrentBin = this;
            if( m_pHead != nullptr ) {
                m_pHead->m_pPrev = pItem;
                pItem->m_pNext = m_pHead;
            }

            m_pHead = pItem;
            return true;
        }

        bool        RemoveFromBin( CItem *pItem ) {
            if( !pItem ) {
                return false;
            }

            if( m_pHead == pItem ) {
                // We're removing the head, repoint to the new one.
                m_pHead = pItem->m_pNext;
            }

            // Just remove us.
            if( pItem->m_pPrev ) {
                pItem->m_pPrev->m_pNext = pItem->m_pNext;
            }
            if( pItem->m_pNext ) {
                pItem->m_pNext->m_pPrev = pItem->m_pPrev;
            }
            pItem->m_pCurrentBin = nullptr;
            pItem->m_pPrev = nullptr;
            pItem->m_pNext = nullptr;
            return true;
        }

        bool        MoveToNextBin( CItem *pItem ) {
            if( !pItem ) {
                return false;
            }
            assert( pItem->m_pCurrentBin == this );

            // See if it's the only item in our list.
            if( OnlyItemInBin( pItem ) ) {
                assert( pItem->m_pPrev == nullptr );
                // We're the only item in this bin.  Let's see
                // if we can simply update our value.
                if( !m_pNextBin || (m_pNextBin->m_BinValue > (m_BinValue + 1) ) ) {
                    pItem->m_Value++;
                    m_BinValue++;
                    return true;
                }

                pItem->m_Value++;
                m_pHead = nullptr;
                m_pNextBin->AddItemToBin( pItem );

                m_pParent->RemoveBin( this );
                return true;
            }

            // Remove the item from the current list.
            RemoveFromBin( pItem );

            // Now re-insert the item in a bin with value + 1
            pItem->m_Value++;
            if( m_pNextBin && (m_pNextBin->m_BinValue == pItem->m_Value) ) {
                // The next bin is already value + 1, so just add us there.
                m_pNextBin->AddItemToBin( pItem );
                return true;
            }

            // The next bin is not value + 1, add a new one after us.
            CValueBin *pNewBin = m_pParent->GetNewBin( pItem->m_Value );
            pNewBin->AddItemToBin( pItem );
            if( m_pNextBin ) {
                m_pNextBin->m_pPrevBin = pNewBin;
            }
            pNewBin->m_pPrevBin = this;
            pNewBin->m_pNextBin = m_pNextBin;
            m_pNextBin = pNewBin;
            return true;
        }

        bool        MoveToPrevBin( CItem *pItem ) {
            if( !pItem ) {
                return false;
            }

            // Let's see if we're the only item in the bin.
            if( OnlyItemInBin( pItem ) ) {
                if( !m_pPrevBin || (m_pPrevBin->m_BinValue < (m_BinValue - 1) ) ) {
                    if( m_BinValue == 1 ) {
                        // Us and the item have to go away.
                        RemoveFromBin( pItem );
                        m_pParent->RemoveBin( this );
                        pItem->m_Value--;
                        return false;
                    }

                    m_BinValue--;
                    pItem->m_Value--;
                    return true;
                }

                RemoveFromBin( pItem );
                pItem->m_Value--;
                m_pPrevBin->AddItemToBin( pItem );
                m_pParent->RemoveBin( this );
                return true;
            }

            RemoveFromBin( pItem );
            pItem->m_Value--;
            if( pItem->m_Value <= 0 ) {
                return false;
            }

            if( m_pPrevBin && (m_pPrevBin->m_BinValue == pItem->m_Value) ) {
                m_pPrevBin->AddItemToBin( pItem );
                return true;
            }

            // Need a new bin, and insert before us.
            CValueBin *pNewBin = m_pParent->GetNewBin( pItem->m_Value );
            pNewBin->AddItemToBin( pItem );
            if( m_pPrevBin ) {
                m_pPrevBin->m_pNextBin = pNewBin;
            }
            pNewBin->m_pNextBin = this;
            pNewBin->m_pPrevBin = m_pPrevBin;
            m_pPrevBin = pNewBin;
            return true;
        }
    };

    CValueBin       *m_pHeadBin = nullptr;
    CValueBin       *m_pTailBin = nullptr;

    unordered_map< string, CItem* > m_ItemMap;
    deque< CValueBin* >             m_BinPool;

    public:
    CValueBin*       GetNewBin( int value ) {
        CValueBin *pNewBin = nullptr;
        if( m_BinPool.size() > 0 ) {
            pNewBin = m_BinPool.back();
            m_BinPool.pop_back();
            pNewBin->m_BinValue = value;
            pNewBin->m_pPrevBin = nullptr;
            pNewBin->m_pNextBin = nullptr;
        } else {
            pNewBin = new CValueBin( value, this );
        }

        if( !m_pHeadBin ) {
            m_pHeadBin = pNewBin;
            m_pTailBin = pNewBin;
        } else {
            if( value < m_pHeadBin->m_BinValue ) {
                m_pHeadBin = pNewBin;
            }
            if( value > m_pTailBin->m_BinValue ) {
                m_pTailBin = pNewBin;
            }
        }

        return pNewBin;
    }

    bool            RemoveBin( CValueBin *pBin ) {
        if( m_pHeadBin == pBin ) {
            m_pHeadBin = pBin->m_pNextBin;
        }
        if( m_pTailBin == pBin ) {
            m_pTailBin = pBin->m_pPrevBin;
        }

        if( pBin->m_pNextBin ) {
            pBin->m_pNextBin->m_pPrevBin = pBin->m_pPrevBin;
        }
        if( pBin->m_pPrevBin ) {
            pBin->m_pPrevBin->m_pNextBin = pBin->m_pNextBin;
        }
        pBin->m_pPrevBin = nullptr;
        pBin->m_pNextBin = nullptr;

        m_BinPool.push_back( pBin );
        return true;
    }

public:
    AllOne() {
    }
    
    void inc(string key) {
        auto currentItem = m_ItemMap.find( key );
        if( currentItem != m_ItemMap.end() ) {
            // Move the current item.
            CItem *pItem = currentItem->second;
            pItem->m_pCurrentBin->MoveToNextBin( pItem );
            return;
        }

        // Make a new item.
        CItem *pNewItem = new CItem( key, 1 );
        CValueBin *pOneBin = nullptr;
        if( m_pHeadBin && (m_pHeadBin->m_BinValue == 1) ) {
            pOneBin = m_pHeadBin;
        } else {
            CValueBin *pOldHead = m_pHeadBin;
            pOneBin = GetNewBin( 1 );
            assert( pOneBin == m_pHeadBin );
            pOneBin->m_pNextBin = pOldHead;
            if( pOldHead ) {
                pOldHead->m_pPrevBin = pOneBin;
            }
        }

        pOneBin->AddItemToBin( pNewItem );
        m_ItemMap.insert( { key, pNewItem } );
    }
    
    void dec(string key) {
        auto currentItem = m_ItemMap.find( key );
        if( currentItem != m_ItemMap.end() ) {
            // Move the current item.
            CItem *pItem = currentItem->second;
            pItem->m_pCurrentBin->MoveToPrevBin( pItem );

            // If we're now 0, remove us.
            if( pItem->m_Value == 0 ) {
                m_ItemMap.erase( currentItem );
                delete pItem;
            }
            return;
        }
    }
    
    string getMaxKey() {
        if( m_pTailBin ) {
            return m_pTailBin->m_pHead->m_Key;
        }   
        return "";     
    }
    
    string getMinKey() {
        if( m_pHeadBin ) {
            return m_pHeadBin->m_pHead->m_Key;
        }
        return "";
    }
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
 */