/** 
 * @param {number} target
 * @return {number}
 */
Array.prototype.upperBound = function(target) {
  let left = 0, right = this.length - 1;
  while (left <= right) {
      const mid = (left + right) >> 1;
      if (this[mid] <= target) {
          left = mid + 1
      } else {
          right = mid - 1
      }
  }
  return --left >= 0 && this[left] == target ? left : -1;
};


// [3,4,5].upperBound(5); // 2
// [1,4,5].upperBound(2); // -1
// [3,4,6,6,6,6,7].upperBound(6) // 5