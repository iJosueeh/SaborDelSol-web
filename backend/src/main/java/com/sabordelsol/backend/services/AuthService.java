package com.sabordelsol.backend.services;

import com.sabordelsol.backend.models.dto.AuthResponse;
import com.sabordelsol.backend.models.dto.LoginRequest;
import com.sabordelsol.backend.models.entity.Usuario;
import com.sabordelsol.backend.repository.UsuarioRepository;
import com.sabordelsol.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        if (!usuario.getRol().startsWith("ROLE_")) {
            usuario.setRol("ROLE_" + usuario.getRol().toUpperCase());
        }

        return usuarioRepo.save(usuario);
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepo.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Correo no encontrado"));

        if (!encoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(usuario.getCorreo(), usuario.getRol());
        return new AuthResponse(token, usuario.getRol());
    }

}
