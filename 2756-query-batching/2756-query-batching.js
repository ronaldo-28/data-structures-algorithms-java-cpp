/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */
var throttle = function (fn, t) {
    let timerId;
    let lastArguments;

    return function throttled(...args) {
        if (!timerId) {
            fn(...args);

            timerId = setTimeout(() => {
                timerId = undefined;
                if (lastArguments) {
                    throttled(...lastArguments)
                    lastArguments = undefined
                }
            }, t)
        } else {
            lastArguments = args;
        }
    }
};

/**
 * const throttled = throttle(console.log, 100);
 * throttled("log"); // logged immediately.
 * throttled("log"); // logged at t=100ms.
 */

/**
 * @param {Function} queryMultiple
 * @param {number} t
 * @return {void}
 */
var QueryBatcher = function(queryMultiple, t) {
    this.keys = []
    this.callbacks = []
    this.run = throttle(async () => {
        const keys = this.keys
        const callbacks = this.callbacks
        this.keys = []
        this.callbacks = []
        const results = await queryMultiple(keys)
        results.forEach((result, index) => {
            callbacks[index](result)
        })
    }, t);
};

/**
 * @param {string} key
 * @return {Promise<string>}
 */
QueryBatcher.prototype.getValue = function(key) {
    return new Promise((resolve) => {
        this.keys.push(key)
        this.callbacks.push(resolve);
        this.run();
    })
};


// async function queryMultiple(keys) { 
//   return keys.map(key => key + '!');
// }
// const batcher = new QueryBatcher(queryMultiple, 100);
// batcher.getValue('a').then(console.log); // resolves "a!" at t=0ms 
// batcher.getValue('b').then(console.log); // resolves "b!" at t=100ms 
// batcher.getValue('c').then(console.log); // resolves "c!" at t=100ms 