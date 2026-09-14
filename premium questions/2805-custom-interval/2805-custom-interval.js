/**
 * @param {Function} fn
 * @param {number} delay
 * @param {number} period
 * @return {number} id
 */

const ids = []
function customInterval(fn, delay, period){
 let len = ids.length
 let p = 0;
  
  function executeFn() {
    fn();
    p += period
    ids[len - 1] = setTimeout(executeFn, p + delay);
  }
  
  ids[len - 1] = setTimeout(executeFn, delay);

  return len - 1; // Return the intervalId directly
}

/**
 * @param {number} id
 */
function customClearInterval(id) {
  clearTimeout(ids[id]);
    
}