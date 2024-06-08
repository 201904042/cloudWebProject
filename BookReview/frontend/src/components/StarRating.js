import React from 'react';

export default function StarRating({ grade }) {
  const stars = [];

  for (let i = 0; i < grade-0.5; i++) {
    stars.push(<div key={i} className="star full-star"></div>);
  }

  if (grade % 1 !== 0) {
    stars.push(<div key="half" className="star half-star"></div>);
  }

  
  return <span className="star-container">{stars}</span>;
}
