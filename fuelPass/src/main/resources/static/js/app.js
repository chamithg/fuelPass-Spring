


function decideQuota() {
    var type = document.getElementById('type').value;
    if(type=="car"){
		document.getElementById('quotaDisplay').innerHTML = 12;
		document.getElementById('quota').value=12;	
		document.getElementById('balance').value=12;
	}
	if(type=="cycle"){
		document.getElementById('quotaDisplay').innerHTML = 8;
		document.getElementById('quota').value=8;	
		document.getElementById('balance').value=8;	
	}
	if(type=="suv"){
		document.getElementById('quotaDisplay').innerHTML = 15;
		document.getElementById('quota').value=15;	
		document.getElementById('balance').value=15;	
	}
	if(type=="truck"){
		document.getElementById('quotaDisplay').innerHTML = 18;
		document.getElementById('quota').value=18;	
		document.getElementById('balance').value=18;	
	}
	if(type=="hybrid"){
		document.getElementById('quotaDisplay').innerHTML = 10;
		document.getElementById('quota').value=10;	
		document.getElementById('balance').value=10;	
	}
    
}



function drawChart(){
	let quota = document.getElementById('quota').innerHTML ;
let balance = document.getElementById('balance').innerHTML ;
console.log(quota)
console.log(balance)

const ctx1 = document.getElementById('myChart1');
            const myChart1 = new Chart(ctx1, {
              type: 'doughnut',
              data: {
              labels: [
                'Already Used',
                'Available balance',
              ],
              datasets: [{
                label: 'My First Dataset',

                data: [quota-balance,balance],
                backgroundColor: [
                  'hsl(0, 100%, 55%)',
                  'hsl(225, 100%, 55%)',
                ],
                hoverOffset: 4
              }] //data sets
            }// data
            
          });
	
}


 

