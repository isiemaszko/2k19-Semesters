// //Izabela Siemaszko

//3.1

const addition=function addition(a,b) 
{
    return a+b;
}
const subtraction=function subtraction(a,b)
{
    return a-b;
}
const multiplication = function multiplication(a,b)
{
    return a*b;
}
const division = function division(a,b)
{
    return a/b;
}
let array1=[3,7,2], array2=[3,1,8];
const CreateTable = function CreateTable(array1,array2,funkcja)
{
    let newarray=new Array(array1.length);
    for(let i=0;i<newarray.length;i++)
    {
        newarray[i]=funkcja(array1[i],array2[i]);
    }
    return newarray;
}

CreateTable(array1,array2,addition);

//3.1a

const Point =function(x,y)
{
    this.x=x;
    this.y=y;
}

const AddValue=function AddValue(array1,array2)
{
    let newarray=new Array(array1.length);
    for(let i=0;i<array1.length;i++)
    {
        let x=array1[i];
        let y=array2[i];
        newarray[i]=new Point(x,y);
    }
    return newarray;
}
AddValue(array1,array2);

//3.1***
let array3=[4,6,0];
const ManyTable=function ManyTable()
{
    let newarray=[];
    newarray=arguments[0];
    let foo=arguments[arguments.length-1];
    for(let i=1;i<arguments.length-1;i++)
    {
        let pomarray=arguments[i];
        for(let j=0;j<newarray.length;j++)
        {   
              newarray[j]=foo(newarray[j],pomarray[j]);
        }
       
    }
     return newarray;
}
ManyTable();

//3.3 sposób z wykładu

//przykład z wykładu
// var inc = (function(){var count=0;return function() {return++count;};})();


function Range(a,b){
        return function(){
            if(a<=b)
            {
                return a++;
            }   
            return NaN;       
        };
    
   
}
var inc=Range(2,5);

console.log(inc());
console.log(inc());
console.log(inc());
console.log(inc());
console.log(inc());
//3.3***

function InverseRange(a,b){
    return function(){
        if(b<=a)
        {
            return a--;
        }   
        return NaN;       
    };


}
var incc=InverseRange(5,2);
// console.log(incc());
// console.log(incc());
// console.log(incc());
// console.log(incc());
// console.log(incc());

//3.2
const ObjectInteger=function(Array)
{
    this.Array=Array;
    this.addInteger=function(number)
    {
        if(isInteger(number)==true)
        {
            for(let i=0;i<Array.length;i++)
            {
                if(Array[i]==number) return;
            }
                this.Array.push(number);
        }
       return;
    }

    this.subtractionInteger=function(number)
    {
        for(let i=0;i<Array.length;i++)
        {
            if(Array[i]==number) 
            {
               Array.splice(i,1);
            }
        }
    }
    this.searchNumber=function(number)
    {
        for(let i=0;i<Array.length;i++)
        {
            if(Array[i]==number)
            {
                return "Liczba "+number+" znajduje się w zbiorze";
            }
        }
        return "Liczba "+number+" nie znajduje się w zbiorze";
    }
    this.showArray=function()
    {
        return Array; 
    }
   
}

const object=new ObjectInteger([]);
object.addInteger(5);
object.addInteger(4);
object.addInteger(6);
object.addInteger(1);

function isInteger(x)
{
    if(x==Math.round(x)) return true;
    
    return false;
}
isInteger();