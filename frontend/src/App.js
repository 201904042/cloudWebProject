import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import BookList from './components/BookList.js';
import { BookListProvider } from "./context/BookContext";
import BookCreateForm from "./components/BookCreateForm"

import BookInform from './components/BookInform.js';
import { ReviewListProvider } from './context/ReveiwContext.js';

function App() {
  return (
    <Router>
      <BookListProvider>
          <ReviewListProvider>
          <div className="container">
          <Routes>
            <Route path="/book" element={<BookList/>} />
            <Route path="/newBook" element={<BookCreateForm />} />
            <Route path="/book/:id" element={<BookInform />} />

          </Routes>
        </div>
          </ReviewListProvider>
      </BookListProvider>
    </Router>
  );
}

export default App;
