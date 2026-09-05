/**
 * @param {Object} obj
 * @return {Function}
 */
Function.prototype.bindPolyfill = function(obj) {
    const context = this;
    return function(...newArgs){
        return context.call(obj, ...newArgs)
    }
}