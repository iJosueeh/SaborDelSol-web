import "./styles/prom.css";
import bebida1 from "../assets/img/Image (4).png";
import chocolate from "../assets/img/chocolate.png";
import dragon from "../assets/img/dragon.png";
import hojita from "../assets/img/hojita.png";

export default function Promociones() {
  return (
    <div
      className="w-100 min-vh-100 overflow-hidden"
      style={{
        background: "linear-gradient(to right, #fdf0e4, #ffb19e)",
      }}
    >
    <div className="container my-5">
      <h2
          className="mb-4"
          style={{
            fontWeight: "bold",
            fontSize: "2rem",
            color: "black",
            borderBottom: "4px solid #f95d1f",
            display: "inline-block",
            paddingBottom: "1px",
            marginLeft: "10px",
            marginTop: "40px"
            
          }}
        >
          PROMOCIONES
         <img src={hojita} alt="hojita" width="80" /></h2>

        <div className="row">
         
            <div className="col-md-4 mb-4 position-relative d-flex justify-content-center">
            {/* Imagen decorativa de hojita */}
            <img
                src={hojita}
                alt="hojita"
                className="hojita-decorativa"
            />

            <div className="card h-100 shadow-sm promo-card">
                <img src={bebida1} className="card-img-top" alt="Promoción 1" />
                <div className="card-body">
                <h5 className="card-title">Promoción 1</h5>
                <p className="card-text">
                    Disfruta de un 20% de descuento en nuestra selección de jugos naturales.
                </p>
                <a href="#" className="btn btn-naranja">Ver Detalles</a>
                </div>
            </div>
        
            </div>
            <div className="col-md-4 mb-4">
                
                
            <div className="card h-100 shadow-sm">
                <img src={chocolate} className="card-img-top" alt="Promoción 2" />
                <div className="card-body">
                <h5 className="card-title">Promoción 2</h5>
                <p className="card-text">Compra uno y lleva otro gratis en nuestra línea de jugos energéticos.</p>
                <a href="#" className="btn btn-naranja">Ver Detalles</a>
                </div>
                
            </div>
            </div>
            
            
            <div className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm">
                <img src={dragon} className="card-img-top" alt="Promoción 3" />
                <div className="card-body">
                <h5 className="card-title">Promoción 3</h5>
                <p className="card-text">Obtén un jugo detox gratis al comprar cualquier combo.</p>
                <a href="#" className="btn btn-naranja">Ver Detalles</a>
                
                </div>
            </div>
            </div>
        </div>
        </div>
        </div>
    );
    }