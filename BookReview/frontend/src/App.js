import BookList from "./components/BookList"
import BookCreateForm from "./components/BookCreateForm"
import { BookListProvider } from "./context/BookContext";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
function App() {
  return (
    <Router>
    <BookListProvider>
      <div className="container">
        <hr />
        <Routes>
          <Route path="/new" element={<BookCreateForm />} />
          <Route path="/" element={<BookList />}>
            <Route index element={<BookList />} />
          </Route>
        </Routes>
      </div>
    </BookListProvider>
  </Router>)
}

export default App;
