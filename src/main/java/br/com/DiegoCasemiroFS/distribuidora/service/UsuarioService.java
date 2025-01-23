package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Usuario;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.LoginRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.UsuarioRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.LoginNaoRealizadoException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UsuarioNaoEncontradoException;
import br.com.DiegoCasemiroFS.distribuidora.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario procuraPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public Usuario procuraPorEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public List<Usuario> listaTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario cadastraUsuario(Usuario usuario) throws LoginException {
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());

        if (user.isEmpty()){
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(usuario.getNome());
            novoUsuario.setEmail(usuario.getEmail());
            novoUsuario.setSenha(usuario.getSenha());
            novoUsuario.setContato(usuario.getContato());
            novoUsuario.setEndereco(usuario.getEndereco());
            novoUsuario.setTipoUsuario(usuario.getTipoUsuario());
            novoUsuario.setAdmin(usuario.isAdmin());

            return usuarioRepository.save(novoUsuario);
        }
        throw new LoginException("Este Usuário já existe");
    }

    public Usuario login(LoginRequestDto requestDto) {
        Usuario usuario = usuarioRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (requestDto.getPassword().equals(usuario.getSenha())){
            return usuario;
        }
        throw new LoginNaoRealizadoException();
    }

    public Usuario alteraUsuario(Long id, UsuarioRequestDto usuarioRequestDto) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setEndereco(usuarioRequestDto.getEndereco());
                    usuario.setContato(usuarioRequestDto.getContato());
                    return usuarioRepository.save(usuario);
                }).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public void deletaUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}