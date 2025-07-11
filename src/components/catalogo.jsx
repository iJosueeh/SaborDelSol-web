import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import fresa from "../assets/img/jfresa.jpg";
import arandano from "../assets/img/jarandano.jpg";
import mango from "../assets/img/jmango.jpg";
import ramdom from "../assets/img/exotico.jpg";
import verde from "../assets/img/verde1.jpg";


export default function Catalogo() {
  const settings = {
    infinite: true,
    autoplay: true,
    autoplaySpeed: 3000,
    slidesToShow: 3,
    slidesToScroll: 1,
    arrows: true,
    //ojito ojite se adapta
    responsive: [
      {
        breakpoint: 992, 
        settings: { slidesToShow: 2 }
      },
      {
        breakpoint: 768, 
        settings: { slidesToShow: 1 }
      }
    ]
  };

  const productos = [
    { nombre: "Jugo de Fresa", descripcion: "Refrescante y natural", imagen: fresa },
    { nombre: "Jugo de Mango", descripcion: "Dulce y tropical", imagen: mango },
    { nombre: "Jugo de Arándano", descripcion: "Rico en antioxidantes", imagen: arandano },
    { nombre: "Jugo Detox", descripcion: "Verde y saludable", imagen: verde },
    { nombre: "Jugo Exótico", descripcion: "Sabores únicos", imagen: ramdom }
  ];

  return (
    <div
      className="w-100 min-vh-100 overflow-hidden"
      style={{
        background: "linear-gradient(to right, #fdf0e4, #ffb19e)",
      }}
    >
      <div className="container py-5" style={{ marginTop: "70px" }}>
        <h2
          className="mb-5"
          style={{
            fontWeight: "bold",
            fontSize: "2rem",
            color: "black",
            borderBottom: "4px solid #f95d1f",
            display: "inline-block",
            paddingBottom: "5px",
            marginLeft: "10px",
          }}
        >
          CATÁLOGO
        </h2>

        {/* Carrusel de productos */}
        <Slider {...settings}>
          {productos.map((producto, index) => (
            <div key={index} className="px-3">
              <div className="card shadow border-0">
                <img
                  src={producto.imagen}
                  className="card-img-top"
                  alt={producto.nombre}
                  style={{ height: "200px", objectFit: "cover", borderRadius: "10px 10px 0 0" }}
                />
                <div className="card-body text-center">
                  <h5 className="card-title">{producto.nombre}</h5>
                  <p className="card-text">{producto.descripcion}</p>
                </div>
              </div>
            </div>
          ))}
        </Slider>
      </div>
    </div>
  );
}
