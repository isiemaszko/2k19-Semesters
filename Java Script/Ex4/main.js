//Izabela Siemaszko

//4.1
let tekst="javascript"

var tabpom = {};

tekst.split('').forEach(function(znak) {
    if(!tabpom.hasOwnProperty(znak)) {
        tabpom[znak] = 0;
    }

    tabpom[znak]++;
});

// console.log(tabpom);

//4.2
const CoDrugi=function(value,index)
{
    return index%2==0;
}
const Scal=function(value,index,array)
{
    let min=Math.min(...array)
    let max=Math.max(...array)
    return Math.round((value-min)/(max-min) * 100) / 100
   
}
let array=[10,45,52,74,21]
const Tab=function Tab(array)
{
    let filtered=array.filter(CoDrugi); 
    return  filtered.map(Scal);
}
Tab(array);

//4.3
const wartosc=function(value)
{
    return  Math.floor(Math.random() * (+11 - +1)) + +1; 
    
}
const iloczyn=function(last,curr)
{
    return last*curr;
}
const TworzTablice=function(rozmiar)
{
    let tablica=new Array(rozmiar)
    tablica.fill(0)
    tablica=tablica.map(wartosc)
    // console.log(tablica);
    return tablica.reduce(iloczyn)
}
TworzTablice()

//4.4
const Konto =function(typ, stan){

    this.stan=stan
    if(typ==1 || typ==3)
    {
        this.wplac=function(wartosc)
        {
            this.stan+=wartosc
            return;
        }
    }
       
    
    if(typ==2 || typ==3)
    {
        this.wyplac=function(wartosc)
        {           
            this.stan-=wartosc
            return;
        }
    }
   
    
}

const Konta=function(ilosc)
{
    let tablicaKont=new Array(ilosc)
    tablicaKont.fill(new Konto(0,0))
    tablicaKont= tablicaKont.map(function(){
         return new Konto(Math.floor(Math.random() * (+4 - +1) + +1),Math.floor(Math.random()* (+1000 - +1) + +1))
    });
    // console.log(tablicaKont);

    tablicaKont.forEach(function(el){
        console.log("obiekt");
        console.log(el);
        if(el.wplac)
        {
            
            el.wplac(50)
            
        }
        if(el.wyplac)
        {
           
            el.wyplac(20)
            
        }
        console.log(el);
    
    });

  
}
Konta(3)

