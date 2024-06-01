 package org.fapalma.pel.services;

import java.util.List;
import java.util.Optional;

import org.fapalma.pel.models.Users;
import org.fapalma.pel.repositories.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServices {
	@Autowired
	private final UsersRepository usersRepository;
	
	public UsersServices(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public List<Users> findAll(){
		return usersRepository.findAll();
	}  
	
	public Users registerUser(Users user) {
		if(usersRepository.findByEmail(user.getEmail()) != null) {
			throw new IllegalArgumentException("No se puede crear al usuario con el Email proporcionado!.");
		}
        String hashed = BCrypt.hashpw(user.getContrasena(), BCrypt.gensalt());
        user.setContrasena(hashed);
        return usersRepository.save(user);
    }
	public Users findUserById(Long id) {
    	Optional<Users> u = usersRepository.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
	public boolean authenticateUser(String username, String password) {
        // primero encontrar el usuario por su nombre de usuario
		Users user = usersRepository.findByUsername(username);
        // si no lo podemos encontrar por su el, retornamos false
        if(user == null || (!user.isActive())) {
            return false;
        } else {
            // si el password coincide devolvemos true, sino, devolvemos false
            if(BCrypt.checkpw(password, user.getContrasena())) {
                return true;
            } else {
                return false;
            }
        }
    }

	public Users updateUsuario(Long id, Users updatedUserData) {
        Optional<Users> optUsuario = usersRepository.findById(id);
        if (optUsuario.isPresent()) {
            Users usuario = optUsuario.get();
            if (usuario.isActive()) {
                usuario.setAdmin(updatedUserData.isAdmin());
                usuario.setEmail(updatedUserData.getEmail());
                usuario.setUsername(updatedUserData.getUsername());
                
                if (updatedUserData.getContrasena() != null && !updatedUserData.getContrasena().isEmpty()) {
                    String hashed = BCrypt.hashpw(updatedUserData.getContrasena(), BCrypt.gensalt());
                    usuario.setContrasena(hashed);
                }

                usuario.setActive(updatedUserData.isActive());
                usuario.setDocente(updatedUserData.isDocente());
                usuario.setTempAcad(updatedUserData.getTempAcad());
                usuario.setFirst_name(updatedUserData.getFirst_name());
                usuario.setLast_name(updatedUserData.getLast_name());
                usuario.setCarreer(updatedUserData.getCarreer());
                usuario.setAsginatura(updatedUserData.getAsginatura());
                usuario.setDireccion(updatedUserData.getDireccion());
                usuario.setTel(updatedUserData.getTel());
                
                return usersRepository.save(usuario);
            }
            throw new IllegalArgumentException("No se puede modificar el usuario con el ID proporcionado.");
        }
        return null;
    }
	
	public Users disableUsuario(Long id) {
		Optional<Users> optUsuarioDel = usersRepository.findById(id);
		if(optUsuarioDel.isPresent()) {
			Users usuario = optUsuarioDel.get();
			usuario.setActive(false);
			return usersRepository.save(usuario);
		}else {
			throw new IllegalArgumentException("No se puede deshabilitar al usuario con el ID proporcionado, ya que este no existe!.");
		}
	}
	public Users enableUsuario(Long id) {
		Optional<Users> optUsuarioDel = usersRepository.findById(id);
		if(optUsuarioDel.isPresent()) {
			Users usuario = optUsuarioDel.get();
			if(!usuario.isActive()) {
				usuario.setActive(true);
				return usersRepository.save(usuario);
			}else {
				throw new IllegalArgumentException("No se puede habilitar al usuario con el ID proporcionado, este ya estaba activo!.");
			}
		}else {
			throw new IllegalArgumentException("No se puede habilitar al usuario con el ID proporcionado!.");
		}
	}
	public void deleteUsuario(Long id) {
		usersRepository.deleteById(id);
	}
	
}
