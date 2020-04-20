import express from 'express';
import cors from 'cors';


const app = express();
app.use(cors());
app.use(express.json());

let book_author=[
  {
    id:1,
    idBook:1,
    idAuthor:1
  },
  {
    id:2,
    idBook:2,
    idAuthor:1
  },
  {
    id:3,
    idBook:3,
    idAuthor:3
  },
  {
    id:4,
    idBook:3,
    idAuthor:2
  },
  {
    id:5,
    idBook:4,
    idAuthor:4
  },

]

let books = [
  {
    id: 1,
    title: "Zwiadowcy. Ruiny Gorlanu",
    publishing: "Jaguar",
    dateOfPublication: "2014-01-01T12:30:00",
    numberInLibrary: 2
  },
  {
    id: 2,
    title: "Zwiadowcy. Płonący most",
    publishing: "Jaguar",
    dateOfPublication: "2016-01-01T12:30:00",
    numberInLibrary: 2
  },
  {
    id: 3,
    title: "Przeżyć w gęstym lesie nauki",
    publishing: "Wild",
    dateOfPublication: "2015-01-23T12:00:00",
    numberInLibrary: 3
  },
  {
    id: 4,
    title: "Druty",
    publishing: "Mila",
    dateOfPublication: "2016-02-10T15:30:30",
    numberInLibrary: 2
  },

];

let authors = [
  {
    id: 1,
    name: "John",
    surname: "Flanagan",
    address: { city: "Sydney", street: "Georgia", house: 5 },
    mail: "johny@wp.pl"
  },
  {
    id: 2,
    name: "Paul",
    surname: "Life",
    address: { city: "Warszawa", street: "Solidarności", house: 10 },
    mail: "lifeislife@wp.pl"
  },
  {
    id: 3,
    name: "Michael",
    surname: "Bush",
    address: { city: "New York", street: "Wall", house: 8 },
    mail: "bush@gmail.com"
  },
  {
    id: 4,
    name: "Izabela",
    surname: "Wybicka",
    address: { city: "Kraków", street: "Smocza", house: 1 },
    mail: "izawy@wp.pl"
  },

];

let employees = [
  {
    id: 1,
    name: "Karol",
    surname: "Miły",
    workplace: "bibliotekarz",
    recruitmentDate: "2012-02-10T15:40:30",
    salary: 2000,
    phone: 123456789
  },
  {
    id: 2,
    name: "Adam",
    surname: "Kasjer",
    workplace: "bibliotekarz",
    recruitmentDate: "2012-02-10T15:30:30",
    salary: 2500,
    phone: 123456780
  },
  {
    id: 3,
    name: "Agnieszka",
    surname: "Sopel",
    workplace: "kierownik",
    recruitmentDate: "2012-02-10T15:20:30",
    salary: 5000,
    phone: 123456785
  },

];

app.get('/books', (req, res) => {
  res.send(books);
});

app.get('/authors', (req, res) => {
  res.send(authors);
});

app.get('/employees', (req, res) => {
  res.send(employees);
});

app.get('/bookAuthor', (req, res) => {
  res.send(book_author);
});

app.get('/booksFind/:title', (req, res) => {
  let title = req.params.title;
  if(title==null||title=='') return res.send(books);
  let find = false;
  let booksTMP=[]
  books.forEach((el) => {
    // let element = title.toLocaleUpperCase();
    // let upperTittle = title.toLocaleUpperCase();
    if (el.title.toLocaleUpperCase().includes(title.toLocaleUpperCase())) {
      booksTMP.push(el);
      find=true;
    }
  })
  if(find) return res.send(booksTMP);
  res.status(304).send({ err: 304 });
});

app.get('/books/:id', (req, res) => {
  let id = req.params.id;
  let found=false;
  books.forEach((el) => {
    if (el.id == id) 
    {
      found=true;
      return res.send(el);

    }
  })
  if(found)return;
  console.log("not found");
  res.status(304).send({ err: 304 });
});


// app.get('/authors/:surname', (req, res) => {
//   let surname = req.params.surname;
//   authors.forEach((el) => {
//     if (el.surname == surname) return res.send(el);
//   })
//   res.status(304).send({ err: 304 });
// });

app.get('/authors/:id', (req, res) => {
  let id = req.params.id;
  let found=false;
  console.log(req);
  console.log(res);
  authors.forEach((el) => {
    if (el.id == id) 
    {
      found=true;
      console.log("found!");
      return res.send(el);
    }
  })
  if(found) return;
  console.log("not found");
  res.status(304).send({ err: 304 });
});

app.get('/bookwithAuthor/:id', (req, res) => {
  let id = req.params.id;
  let found=false;
  let authorsTMP = []
  console.log(req);
  console.log(res);
  book_author.forEach((el) => {
    if (el.idBook == id) 
    {
      found=true;

      let aut = authors.find(x => x.id==el.idAuthor);
      if(aut) authorsTMP.push(aut);
      console.log("found!");
      //return res.send(el);
    }
  })
  if(found) return res.send(authorsTMP);;
  console.log("not found");
  res.status(304).send({ err: 304 });
});

app.get('/authorWithBooks/:id', (req, res) => {
  let id = req.params.id;
  let found=false;
  let booksTMP = []
  console.log(req);
  console.log(res);
  book_author.forEach((el) => {
    if (el.idAuthor == id) 
    {
      found=true;

      let boo = books.find(x => x.id==el.idBook);
      if(boo) booksTMP.push(boo);
      console.log("found!");
      //return res.send(el);
    }
  })
  if(found) return res.send(booksTMP);;
  console.log("not found");
  res.status(304).send({ err: 304 });
});

app.get('/employees/:id', (req, res) => {
  let id = req.params.id;
  let found=false;
  employees.forEach((el) => {
    if (el.id == id) 
    {
      found=true;
      return res.send(el);
    }
  })
  if(found)return;
  console.log("not found");
  res.status(304).send({ err: 304 });
});


// app.get('/tasks/cats/:category', (req, res) => {
//   let category = req.params.category;
//   let catTasks = tasks.filter((el) => el.category === category);
//   console.log(catTasks);
//   if (catTasks.length == 0) { res.status(304).send({ err: 304 }) }
//   else res.send(catTasks);
// });

app.post('/bookAuthors', (req, res) => {
  const bookAuthor = req.body;
  let dupl=false;
  book_author.forEach(el=>{
    if(el.idBook===bookAuthor.idBook && el.idAuthor===bookAuthor.idAuthor) {
      res.sendStatus(304);
      dupl=true;
    }
  });
  if(!dupl){
    console.log(bookAuthor);
    bookAuthor.id=book_author.length+1;
    book_author.push(bookAuthor);
    res.send(bookAuthor);
  }
});

app.post('/books', (req, res) => {
  const book = req.body;
  let dupl=false;
  books.forEach(el=>{
    if(el.title===book.title) {
      res.sendStatus(304);
      dupl=true;
    }
  });
  if(!dupl){
    console.log(book);
    book.id=books.length+1;
    books.push(book);
    res.send(book);
  }
});

app.post('/authors', (req, res) => {
  const author = req.body;
  let dupl=false;
  authors.forEach(el=>{
    if(el.name===author.name && el.surname===author.surname) {
      res.sendStatus(304);
      dupl=true;
    }
  });
  if(!dupl){
    console.log(author);
    author.id=authors.length+1;
    authors.push(author);
    res.send(author);
  }
});

app.post('/employees', (req, res) => {
  const employee = req.body;
  let dupl=false;
  employees.forEach(el=>{
    if(el.name===employee.name && el.surname===employee.surname) {
      res.sendStatus(304);
      dupl=true;
    }
  });
  if(!dupl){
    console.log(employee);
    employee.id=employees.length+1;
    employees.push(employee);
    res.send(employee);
  }
});


app.put('/books', (req, res) => {
  const book = req.body;
  let present = false;
  books.map((el, index, tab) => {
    if (el.id == book.id) {
      // if(book.authors)
      // tab[index].authors = book.authors;

      if(book.publishing)
      tab[index].publishing = book.publishing;

      if(book.dateOfPublication)
      tab[index].dateOfPublication = book.dateOfPublication;

      if(book.title)
      tab[index].title = book.title;

      if(book.numberInLibrary)
      tab[index].numberInLibrary = book.numberInLibrary;
      res.send(tab[index]);
      present = true;
    }
  });
  if (!present) res.sendStatus(304);
});

app.put('/authors', (req, res) => {
  const author = req.body;
  let present = false;
  authors.map((el, index, tab) => {
    if (el.id==author.id) {
      if(author.address.city)
      tab[index].address.city = author.address.city;

      if(author.address.street)
      tab[index].address.street = author.address.street;

      if(author.address.house)
      tab[index].address.house = author.address.house;

      if(author.name)
      tab[index].name = author.name;

      if(author.surname)
      tab[index].surname = author.surname;

      if(author.mail)
      tab[index].mail = author.mail;
      
      res.send(tab[index]);
      present = true;
    }
  });
  if (!present) res.sendStatus(304);
});

app.put('/employees', (req, res) => {
  const employee = req.body;
  let present = false;
  employees.map((el, index, tab) => {
    if (el.name === employee.name && el.surname === employee.surname) {
      if(employee.workplace)
      tab[index].workplace = employee.workplace;

      if(employee.recruitmentDate)
      tab[index].recruitmentDate = employee.recruitmentDate;

      if(employee.salary)
      tab[index].salary = employee.salary;
      
      if(employee.phone)
      tab[index].phone = employee.phone;
      res.send(tab[index]);
      present = true;
    }
  });
  if (!present) res.sendStatus(304);
});



app.delete("/authors/:id", (req, res) => {
   let id = req.params.id;
 // const author=req.body;
 console.log(id)
  let idx=-1;
  authors.map((el, index, tab) => {
    if (el.id==id) {
      idx=index;
    }});
    console.log(idx)

    if(idx!=-1){

    authors.splice(idx,1);
    for(let index=0;index<book_author.length;index++){
      if(book_author[index].idAuthor==id){
        book_author.splice(index,1);
        index--;
      }
    }
    
    return res.send(authors);

    }
  return res.sendStatus(304);
});

app.delete("/books/:id", (req, res) => {
  let id = req.params.id;
// const author=req.body;
console.log(id)
 let idx=-1;
 books.map((el, index, tab) => {
   if (el.id==id) {
     idx=index;
   }});
   console.log(idx)

   if(idx!=-1){

   books.splice(idx,1);

   for(let index=0;index<book_author.length;index++){
     if(book_author[index].idBook==id){
       book_author.splice(index,1);
       index--;
     }
   }
   
   return res.send(books);

   }
 return res.sendStatus(304);
});



app.listen(8080, () =>
  console.log('Example app listening on port 8080!'),
);
