import React from "react";
import { FaFacebookF, FaInstagram, FaPhoneAlt, FaEnvelope } from "react-icons/fa";

export default function Footer() {
  return (
    <footer style={{ backgroundColor: "#f95d1f", color: "white", marginTop: 0 }} className="pt-4">
      <div className="container px-4">
        <div className="row text-center text-md-start">
          <div className="col-md-4 mb-3">
            <h5 className="fw-bold">Sabor del Sol</h5>
            <p style={{ fontSize: "0.9rem" }}>
              Disfruta el verdadero sabor del verano. Jugos naturales, frescos y energéticos.
            </p>
          </div>

          <div className="col-md-4 mb-3">
            <h6 className="fw-bold mb-2">Contáctanos</h6>
            <p style={{ fontSize: "0.9rem", marginBottom: "4px" }}>
              <FaPhoneAlt className="me-2" /> +51 999 888 777
            </p>
            <p style={{ fontSize: "0.9rem", marginBottom: "4px" }}>
              <FaEnvelope className="me-2" /> contacto@sabordelsol.com
            </p>
          </div>

          <div className="col-md-4 mb-3">
            <h6 className="fw-bold mb-2">Legal y redes</h6>
            <ul className="list-unstyled" style={{ fontSize: "0.9rem" }}>
              <li>
                <a href="#" className="text-white text-decoration-none">Términos y condiciones</a>
              </li>
              <li>
                <a href="#" className="text-white text-decoration-none">Política de privacidad</a>
              </li>
            </ul>
            <div className="d-flex gap-3 mt-2">
              <a href="https://facebook.com/sabordelsol" className="text-white fs-5"><FaFacebookF /></a>
              <a href="https://instagram.com/sabordelsol" className="text-white fs-5"><FaInstagram /></a>
            </div>
          </div>
        </div>
      </div>

      <div
        className="text-center py-3"
        style={{
          backgroundColor: "#e84b1b",
          fontSize: "0.85rem",
          marginTop: 0,
          borderTop: "none"
        }}
      >
        © 2023 Sabor del Sol. Todos los derechos reservados.
      </div>
    </footer>
  );
}
