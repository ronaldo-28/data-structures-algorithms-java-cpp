/**
 * @param {Object|Array} obj
 * @return {Object}
 */
var invertObject = function(obj) {
    const invertedObj = {};

    // Loop through each key in the given object or array
    for (let key in obj) {
        // Get the value associated with the current key
        let value = obj[key];

        // Check if the value already exists as a key in invertedObj
        if (invertedObj[value]) {
            // If the value already exists and is an array, push the current key to it
            // If the value exists but isn't an array, convert it to an array
            // and add the current key to the array
            Array.isArray(invertedObj[value]) ? invertedObj[value].push(key) : invertedObj[value] = [invertedObj[value], key];
        } else {
            // If the value doesn't exist in invertedObj, add it with the current key as the value
            invertedObj[value] = key;
        }
    }

    return invertedObj;
};