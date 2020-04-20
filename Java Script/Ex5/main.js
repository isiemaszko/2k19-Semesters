//Izabela Siemaszko

//5.1
let colors=['red', 'orange','yellow','green','blue','purple']

var doc=document.getElementsByTagName("p")
var headlines=document.getElementsByTagName("h2")
var textbox=document.getElementsByTagName("input")
var button=document.getElementsByTagName("button")
    
//5.2
function setColor(){
      for(let i=0;i<doc.length;i++)
      {
           doc[i].style.color=colors[Math.floor(Math.random()*colors.length)]
      }
}
//5.3
function setTitle()
{
      for(let i=0;i<doc.length;i++)
      {
            doc[i].title=doc[i].innerHTML.length;
      }   
}
//5.4
function addBorder(){
      for(let i=0;i<doc.length;i++)
      {
      
            doc[i].onclick=function (){
                   
                  for(let j=0;j<doc.length;j++)
                  {
                        doc[j].style.border='none'
                        doc[j].style.background='white'
                  }
                  if(i%2==0)
                  {
                        doc[i].style.border="thick solid #78b785"
                        doc[i].style.background="#d0cfc8"
                  }
                  else{
                        doc[i].style.border="thick solid #78b785"
                        doc[i].style.background="#57564c"
                  }
                  
                  if(i==0)
                  {
                        var pom=i+1;
                        doc[pom].style.border="thick solid #3e68c5"
                  }
                  else if(i==doc.length-1) 
                  {
                        var pom2=i-1;
                        doc[pom2].style.border="thick solid #d35324"
                  }
                else{
                  var pom2=i-1;
                  doc[pom2].style.border="thick solid #d35324"
                  var pom=i+1;
                  doc[pom].style.border="thick solid #3e68c5"
                }
            };
      }
}
//5.5
function showParagraph(){
      for(let i=0;i<doc.length;i++)
      {
            headlines[i].onclick=function(){
                  if(doc[i].style.display=="none"){
                        doc[i].style.display=""
                  }
                  else{
                        doc[i].style.display="none"
                  }
                
            };
      }
}

//5.6
function addParagraphs()
{
      button[0].onclick=function (){

      var nag=document.createElement("h2")
      var textnag=document.createTextNode("Nagłówek "+(doc.length+1))
      nag.appendChild(textnag)
      var elemnag=document.getElementById("div");
      elemnag.appendChild(nag)
      var p=document.createElement("P")
      var text=document.createTextNode(textbox[0].value)
      p.appendChild(text)
      var elem=document.getElementById("div");
      elem.appendChild(p)
      
      setColor()
      setTitle()
      showParagraph()
      addBorder();
      }
}

      setColor()
      setTitle()
      showParagraph()
      addBorder();
      addParagraphs()
