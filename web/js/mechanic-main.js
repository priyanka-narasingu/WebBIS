/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// TODO: on load, need to ensure that the user is properly logged on, if not, redirect back to index.html

$(document).ready(
    // Clear text fields and disable button
    function() {             
        
        var array= { 
            "#stock": "mechanic-enquire-stock-summary.html",
            "#transaction": "mechanic-enquire-transaction.html",             
            "#usage": "mechanic-enter-usage-detail.html"                                                   
        };
                
        for (var key in array) {                    
            $(key).on("click", function(event) {
                event.preventDefault();                
                window.location.href =array["#" + $(this).get(0).id];
            });                 
        }    
    });
    

        


