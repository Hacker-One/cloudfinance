var watch = require('watch');
var drakov = require('./drakov');

module.exports = function (watchDir) {
  try {
    var filepath;
    watch.watchTree(watchDir, function (f, curr, prev) {
      if (typeof f == "object" && prev === null && curr === null) {
        // Finished walking the tree
        drakov('')
      } else if (prev === null) {
        // f is a new file
        if (filepath != f) {
          drakov(f, 'new');
          filepath = f;
        }
      } else if (curr.nlink === 0) {
        if (filepath != f) {
          // f was removed
          drakov(f)
        }
      } else {
        // f was changed
        drakov(f)
      }
    })
    console.log("watching file...");

  } catch (e) {
    console.log(e.message);
  } finally { }

}
