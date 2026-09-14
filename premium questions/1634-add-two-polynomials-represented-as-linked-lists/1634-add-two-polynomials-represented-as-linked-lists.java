/**
 * Definition for polynomial singly-linked list.
 * class PolyNode {
 *     int coefficient, power;
 *     PolyNode next = null;
 
 *     PolyNode() {}
 *     PolyNode(int x, int y) { this.coefficient = x; this.power = y; }
 *     PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next = next; }
 * }
 */

class Solution {
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        return poly1 == null ? poly2 : poly2 == null ? poly1 : poly1.power > poly2.power ? new PolyNode(poly1.coefficient, poly1.power, addPoly(poly1.next, poly2)) : poly1.power < poly2.power ? new PolyNode(poly2.coefficient, poly2.power, addPoly(poly1, poly2.next)) : poly1.coefficient + poly2.coefficient == 0 ? addPoly(poly1.next, poly2.next) : new PolyNode(poly1.coefficient + poly2.coefficient, poly1.power, addPoly(poly1.next, poly2.next));
    }
}