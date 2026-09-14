/**
 * @param {Array<Function>} functions
 * @param {number} ms
 * @return {Array<Function>}
 */
var delayAll = function(functions, ms) {
    return functions.map(f => async () => {
        await new Promise(resolv => setTimeout(resolv, ms))
        return await f();
    });
};
