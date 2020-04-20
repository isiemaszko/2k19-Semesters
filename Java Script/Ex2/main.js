// //Izabela Siemaszko
let number, table;
// //2.1
const RandomInteger=function RandomInteger(a,b)
{
    if(a<=b)
    {
        a=Math.ceil(a);
        b=Math.floor(b);
        number=Math.floor(Math.random() * (+b - +a)) + +a;  
        return number; 
    }
    else{
        a=Math.floor(a);
        b=Math.ceil(b);
        number=Math.floor(Math.random() * (+a - +b)) + +b;  
        return number; 
    } 
}
RandomInteger();

// //2.2
// table=[0,0,0,0,0,0,0,0,0,0];

// for(let i=0;i<1000000;i++)
// {
//     number=RandomInteger(1,11)
//     table[number-1]++;
// }
// let pom;
// for(let i=0;i<10;i++)
// {
//     pom=i+1;
//      console.log("Wartość "+ pom +" została wylosowan " + table[i] + " razy");
// }

// //2.3
// const RandomTable = function RandomTable(number, min, max)
// {
//     table = new Array(number);
//     for(let i=0;i<table.length;i++)
//     {
//         table[i]=RandomInteger(min,max);
//     }
// }
// RandomTable(12,1,5);

//  console.log(table);

// // // //2.4
// let minimum=99999, maximum=0, śr=0; parz=0; nieparz=0, sum=0;

// for(let i=0;i<table.length;i++)
// {
//     if(table[i]<minimum) { minimum=table[i]; }
//     if(table[i]>maximum) { maximum=table[i]; }
//     if(table[i]%2===0) { parz++; }
//     else { nieparz++; }
//     sum+=table[i];
// }
// śr=sum/number;
// console.log("Minimalna wartość: "+ minimum);
// console.log("Maksymalna wartość: "+ maximum);
// console.log("Średnia wartość: "+ śr);
// console.log("Ilość parzystych liczb: "+ parz);
// console.log("Ilość nieparzystych liczb: "+ nieparz);

// // // //2.5
// const ScalTable = function ScalTable(table)
// {
//     let NewTable = new Array(table.length);
//     for(let i=0;i<table.length;i++)
//     {
//         NewTable[i]=(table[i]-minimum)/(maximum-minimum);
//     }
//     return NewTable;
// }
// ScalTable(table);


// //2.6
let array= new Array(20);
let x,y;
const Point =function(x,y)
{
    this.x=x;
    this.y=y;
}
const CreateTable = function CreateTable()
{
    for(let i=0;i<array.length;i++)
    {
        x=Math.random() * (+2 - +0) + +0; 
        y=Math.random() * (+2 - +0) + +0; 
        array[i]= new Point(x,y);
    }
}


CreateTable();
console.log(array);

// // //2.7
let dis;
const AddDistance = function AddDistance()
{
    for(let i=0;i<array.length;i++)
    {
        dis=Math.sqrt(Math.pow(array[i].x,2)+Math.pow(array[i].y,2))
        array[i].distance=dis;
    }
}

AddDistance();
console.log(array);