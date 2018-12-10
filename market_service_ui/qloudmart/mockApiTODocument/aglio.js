var aglio = require('aglio');
var path = require('path');
var fs = require('fs');
var aglio_options = {
  themeTemplate: 'default',//aglio样式
  locals: {
    myVariable: 125
  }
};
module.exports = function(mdFileUrl) {
  if (mdFileUrl) {
    console.log(mdFileUrl)
    var targetUrl = mdFileUrl.replace("/md/", "/html/").replace(".md", ".html");
    aglio.renderFile(mdFileUrl, targetUrl, aglio_options, function(err, warnings) {
        if (err)
          return console.log(err);
      }
    );
  }

}
