import React from "react";
import "./styles/Hero.css";
import jugos from "../assets/img/jugos.png";
import lima from "../assets/img/Ellipse 1.png";
import naraja from "../assets/img/naraja.png";
import papaya from "../assets/img/papaya.png";
import verde from "../assets/img/verde.png";
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";

export default function Hero() {
  return (
    <div
      className="w-100 min-vh-100 overflow-hidden"
      style={{
        background: "linear-gradient(to right, #fdf0e4, #ffb19e)",
      }}
    >
      {/* Hero principal */}
      <div className="container-fluid px-5 d-flex flex-column flex-lg-row align-items-center justify-content-between position-relative" >
        
        <div className="text-center text-lg-start mb-4 mb-lg-0">
          <h1 className="display-4 fw-bold text-uppercase">
            Sabor del <span style={{ color: "#f95d1f" }}>Sol</span>
          </h1>
          <h2 className="h4 fw-light text-dark">
            Disfruta del <span style={{ color: "#ff7750", fontWeight: "600" }}>verdadero</span> sabor del verano
          </h2>
          <div className="mt-4 d-flex gap-3 justify-content-center justify-content-lg-start">
            <button className="btn" style={{
              backgroundColor: "#f95d1f",
              color: "white",
              borderRadius: "20px",
              padding: "10px 20px",
            }}>Ver Carta</button>
            <button className="btn" style={{
              backgroundColor: "#f95d1f",
              color: "white",
              borderRadius: "20px",
              padding: "10px 20px",
            }}>Contactar</button>
          </div>
        </div>
        
        <div className="w-50 w-lg-50">
          <img src={jugos} alt="Jugos" className="img-fluid w-100" />
        </div>
      </div>
     {/* Slider */}
        <div
          className="slider-container position-absolute start-50 translate-middle-x px-3"
        >
          <div className="slider-inner d-flex align-items-center justify-content-between px-4 py-3">
            <button className="slider-btn me-2">
              <FaArrowLeft />
            </button>

            <div className="slider-scroll d-flex gap-4 px-2">
              {[
                { label: "NATURALES", img: lima },
                { label: "ENERGÉTICOS", img: naraja },
                { label: "DETOX", img: papaya },
                { label: "PROMO", img: verde },
              ].map((item, index) => (
                <div key={index} className="text-center">
                  <img
                    src={item.img}
                    alt={item.label}
                    className="rounded-circle mb-2"
                  />
                  <p className="slider-label">{item.label}</p>
                </div>
              ))}
            </div>

            <button className="slider-btn ms-2">
              <FaArrowRight />
            </button>
          </div>
        </div>
        <button className="slider-btn"><FaArrowRight className="icono" /></button>
      </div>

      
    </div>
  );
}
