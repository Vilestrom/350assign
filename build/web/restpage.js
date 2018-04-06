/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

fetch('./api/RestMessages').then(
        function(res){
            if(res.status !== 200){
                console.log('whoops');
                return;
            }
            res.json().then(function(data){
                for(let x of data){
                    let y = document.createElement("p");
                  y.textContent = "("+x.name+") "+x.message;
                  document.body.appendChild(y);
                }
            });
        }
    ); 
      let btn = document.getElementById("btn");
      let name = document.getElementById("name");
      let message = document.getElementById("message");
      btn.addEventListener("click",function(){
          let obj = {name:name.value,message:message.value};
          fetch('./api/RestMessages',{
        method: 'post',
        headers: {
             'Content-Type': 'application/json; charset=utf-8'
         },
        body: JSON.stringify(obj)
        }).then(function (data){
            location.reload(true);
        });
        });