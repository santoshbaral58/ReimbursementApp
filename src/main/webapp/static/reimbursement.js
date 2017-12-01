function viewAllReimbursement(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = () => {
        console.log(`state changed ${xhttp.readyState}`);
        if(xhttp.readyState === 4 && xhttp.status === 200) {
          console.log('we have the response ready');
          console.log(`response text: ${xhttp.responseText}`)
          let reimbursements = JSON.parse(xhttp.responseText);
          let html = "<table border='2|2'>";
          for (let i = 0; i < reimbursements.length; i++) {
              html+="<tr>";
              html+="<td>"+reimbursements[i].reimbID+"</td>";
              html+="<td>"+reimbursements[i].amount+"</td>";
              html+="<td>"+reimbursements[i].submitted+"</td>";
              html+="<td>"+reimbursements[i].resolved+"</td>";
              html+="<td>"+reimbursements[i].description+"</td>";
              html+="<td>"+reimbursements[i].author+"</td>";
              html+="<td>"+reimbursements[i].resolver+"</td>";
              html+="<td>"+reimbursements[i].statusID+"</td>";
              html+="<td>"+reimbursements[i].typeID+"</td>";
              
              html+="</tr>";
      
          }
          html+="</table>";
          document.getElementById("viewAllReimbursement").innerHTML = html;
        }
    }
    xhttp.open('GET', 'http://localhost:8080/ReimbursementApp/static/');
    xhttp.send(); 
}