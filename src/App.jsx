import Navbar from './components/Navbar'
import Hero from './components/Hero';
import Promociones from './components/promociones';
import About from './components/about';
import Catalogo from './components/catalogo';
import './App.css';
import Footer from './components/footer';

function App() {
  return (
    <div>
      <Navbar />

      <div id="inicio">
       <Hero />
      </div>

      <div id="promociones">
        <Promociones />
      </div>

      <div id="about">
      <About />
      </div>

      <div id="catalogo">
        <Catalogo />
      </div>
      
      <div id="footer">
     <Footer />
     </div>


    </div>
  );
}

export default App;
