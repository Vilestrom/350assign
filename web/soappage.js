/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

this['browser-soap'].createClient("/350assign3/SoapMessages?WSDL", function(err, client) {
  if (!err){
      client.get({}, function(err,results){
          if(!err){
              for(let x of results.return){
                  let y = document.createElement("p");
                  y.textContent = "("+x.name+") "+x.message;
                  document.body.appendChild(y);
              }
          }
      });
      let btn = document.getElementById("btn");
      let name = document.getElementById("name");
      let message = document.getElementById("message");
      btn.addEventListener("click",function(){
          client.post2({user:name.value,message:message.value},function(err,body){
              location.reload(true);
          });
      });
  }
});



