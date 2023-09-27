import React, { useState } from 'react';

function BookList({ books }) {
  return (
    <div>
      <h2>Search Results</h2>
      <ul className="book-list">
        {books.map((book) => (
          <li key={book.id} className="book-item">
            <div className="book-details">
              <h3>{book.title}</h3>
              <p>Author: {book.author}</p>
              <p>Published Year: {book.publishedYear}</p>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BookList;
