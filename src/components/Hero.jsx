import React from "react";
import './style/Hero.css';
import jugos from "../assets/img/jugos.png";
import logo from "../assets/img/logo.png"; 
import { FaHome, FaUtensils, FaStar, FaUser, FaShoppingCart,FaArrowRight,FaArrowLeft } from "react-icons/fa";

export default function Hero() {
  return (
    <div className="hero-container">
      {/* Navbar */}
      <nav className="navbar">
         <div className="logo">
         <img src={logo} alt="Logo Sabor del Sol" className="logo-img" />
        </div>
        <ul className="nav-links">
        <li><FaHome className="icono"/> Inicio</li>
        <li><FaUtensils className="icono" /> Catálogo</li>
        <li><FaStar className="icono" /> Promociones</li>
        <li><FaUser className="icono"/></li> 
        <li><FaShoppingCart className="icono"/></li> 
        </ul>
      </nav>

      <div className="hero-main">
        <div className="hero-text">
          <h1>
            SABOR DEL <span className="highlight">SOL</span>
          </h1>
          <h2>
            DISFRUTA DEL <span className="highlight">VERDADERO</span> SABOR DEL VERANO
          </h2>
          <div className="hero-buttons">
            <button className="btn-primary">Ver Carta</button>
            <button className="btn-secondary">Contactar</button>
          </div>
        </div>
        <div className="hero-image">
          <img src={jugos} alt="Jugos" />
        </div>
      </div>

      {/* Slider */}
      <div className="slider">
        <button className="slider-btn"><FaArrowLeft className="icono"/></button>
        <div className="slider-items">
          {[1, 2, 3, 4].map((item, index) => (
            <div key={index} className="slider-item">
              <img
                src={`https://via.placeholder.com/80?text=Img+${item}`}
                alt="natural"
              />
              <p>NATURALES</p>
            </div>
          ))}
        </div>
        <button className="slider-btn"><FaArrowRight className="icono"/></button>
      </div>
    </div>
  );
}

