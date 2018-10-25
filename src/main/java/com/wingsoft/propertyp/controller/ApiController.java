package com.wingsoft.propertyp.controller;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.wingsoft.propertyp.dao.*;
import com.wingsoft.propertyp.model.*;
import com.wingsoft.propertyp.util.ExtraJson;

import com.wingsoft.propertyp.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
//@EnableJpaRepositories(basePackageClasses= {UserDao.class})

@EnableJpaRepositories("com.wingsoft.propertyp.dao")
@EntityScan("com.wingsoft.propertyp.model")
public class ApiController {

    @Autowired
    PropiedadDao propiedadDao;

    @Autowired
    ComunaDao comunaDao;

    @Autowired
    RegionDao regionDao;

    @Autowired
    VendedorDao vendedorDao;

    @Autowired
    UserDao userDao;

    @Autowired
    NotificaionDao notificaionDao;

    @Autowired
    FilterDao filterDao;

    @Autowired
    BarriosDao barriosDao;


    @RequestMapping(value = "/registro", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse> registro(HttpServletRequest request , @RequestBody User user){
        boolean existe = userDao.existsByEmail(user.getEmail());
        if(!existe){
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getClave().toCharArray());
            user.setClave(bcryptHashString);


            user.setToken(token());
            userDao.save(user);

            List<User> users = new ArrayList<User>();
            users.add(user);
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("Usuario Creado correctamente");
            jsonResponse.setData(users);
            jsonResponse.setError(false);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }else{
            User userDos = userDao.findByEmail(user.getEmail());
            System.out.println(user.getEmail());
            if(user != null){
                System.out.println(userDos.getTipo());
                System.out.println(user.getTipo());
                if(userDos.getTipo().equals(user.getTipo())){

                    BCrypt.Result result = BCrypt.verifyer().verify(user.getClave().toCharArray(), userDos.getClave());
                    if(result.verified){
                        String generatedString = token();
                        userDos.setToken(generatedString);
                        userDos.setDevice(user.getDevice());
                        userDos.setDevice_token(user.getDevice_token());
                        userDao.save(userDos);
                        List<User> users = new ArrayList<User>();
                        users.add(userDos);
                        JsonResponse jsonResponse = new JsonResponse();
                        jsonResponse.setMensaje("Usuario corecto");
                        jsonResponse.setData(users);
                        jsonResponse.setError(false);
                        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
                    }
                    JsonResponse jsonResponse = new JsonResponse();
                    jsonResponse.setMensaje("Password incorrector");
                    jsonResponse.setError(true);
                    return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

                }else{
                    JsonResponse jsonResponse = new JsonResponse();
                    jsonResponse.setMensaje("Este mail tiene una cuenta creada con "+userDos.getTipo());
                    jsonResponse.setError(true);
                    return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
                }

            }else{
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setMensaje("Email incorrecto");
                jsonResponse.setError(true);
                return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
            }
        }
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse> login(HttpServletRequest request){
        //List<User> books12;
        User user = null;
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
       // String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        System.out.println(email);
        user = userDao.findByEmail(email);
        if(user != null){
            if(user.getTipo().equals("Email")){
                BCrypt.Result result = BCrypt.verifyer().verify(clave.toCharArray(), user.getClave());
                if(result.verified){
                    String generatedString = token();
                    System.out.println("token "+generatedString);

                    user.setToken(generatedString);
                    userDao.save(user);
                    List<User> users = new ArrayList<User>();
                    users.add(user);
                    JsonResponse jsonResponse = new JsonResponse();
                    jsonResponse.setMensaje("Usuario corecto ");
                    jsonResponse.setData(users);
                    jsonResponse.setError(false);
                    return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
                }
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setMensaje("Password incorrector");
                jsonResponse.setError(true);
                return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
            }else{
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setMensaje("Este mail tiene una cuenta creada con "+user.getTipo());
                jsonResponse.setError(true);
                return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

            }



        }else{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("Email incorrecto");
            jsonResponse.setError(true);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }




    @RequestMapping(value = "/getAllProperty", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getAllProperty(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            System.out.println(user.getEmail());
            Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "direccion"));
            Pageable pageable = new PageRequest(0, 2, sort);
            Iterable<Propiedad> p = propiedadDao.findAll(pageable);
            List<ExtraJson> propiedads = new ArrayList<ExtraJson>();
            for(Propiedad pro : p){
                //pro.setVendedorJson(pro.getVendedor());
                ExtraJson ex = new ExtraJson();
                //System.out.println(pro.getVendedor().getNombre());
                ex.setData(pro);
                Vendedor v = new Vendedor() ;

                ex.setExtra(pro.getVendedors().get(0));

                propiedads.add(ex);
            }
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedads);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAllPropertySearch", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getAllPropertySearch(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String buscar =request.getHeader("buscar");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            Comuna comuna = null ;
            List<Propiedad> p ;
            comuna = comunaDao.findByNombre(buscar);
            Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "direccion"));
            Pageable pageable = new PageRequest(0, 2, sort);
            if(comuna == null ){
                p = propiedadDao.findByDireccion(buscar,pageable);
            }else{
                p = propiedadDao.findByComuna(comuna,pageable);
            }
            System.out.println(user.getEmail());
            List<ExtraJson> propiedads = new ArrayList<ExtraJson>();
            for(Propiedad pro : p){
                //pro.setVendedorJson(pro.getVendedor());
                ExtraJson ex = new ExtraJson();
                //System.out.println(pro.getVendedor().getNombre());
                ex.setData(pro);
                Vendedor v = new Vendedor() ;
                ex.setExtra(pro.getVendedors().get(0));
                propiedads.add(ex);
            }
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedads);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/getFavaritosByUser", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getFavaritosByUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            System.out.println(user.getEmail());
            Iterable<Propiedad> p = user.getPropiedads();
            List<ExtraJson> propiedads = new ArrayList<ExtraJson>();
            for(Propiedad pro : p){
                //pro.setVendedorJson(pro.getVendedor());
                ExtraJson ex = new ExtraJson();
                //System.out.println(pro.getVendedor().getNombre());
                ex.setData(pro);
                Vendedor v = new Vendedor() ;

                ex.setExtra(pro.getVendedors().get(0));


                propiedads.add(ex);
            }
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedads);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllRegion", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getAllRegion(HttpServletRequest request, HttpServletResponse response){
        String authHeader = request.getHeader("Authorization");
        response.setContentType("application/json");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            Iterable<Region> p = regionDao.findAll();
            List<Region> propiedads = new ArrayList<Region>();
            for(Region pro : p){
                propiedads.add(pro);
            }
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedads);

            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getComunaById", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getAllComuna(HttpServletRequest request, HttpServletResponse response){
        String authHeader = request.getHeader("Authorization");
        long id = Long.parseLong(request.getParameter("id"));
        response.setContentType("application/json");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            Optional optional = null ;
            optional= regionDao.findById(id);
            if(optional.isPresent()){
                Iterable<Comuna> p = comunaDao.findByRegion((Region)optional.get());
                List<Comuna> propiedads = new ArrayList<Comuna>();
                for(Comuna pro : p){
                    propiedads.add(pro);
                }
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setMensaje("data enviados");
                jsonResponse.setData(propiedads);
                return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
            }else{
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setMensaje("id de region invalida");
                jsonResponse.setError(true);
                return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
            }
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }



    @RequestMapping(value = "/createPropiedad", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse> createPropiedad(HttpServletRequest request, @RequestBody Propiedad propiedad){
        System.out.println(propiedad.toString());
        System.out.println(propiedad.getComuna().getId());
        //System.out.println(propiedad.getVendedor().getId());
        String authHeader = request.getHeader("Authorization");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            propiedadDao.save(propiedad);
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("Propiedad creado Correctamente");
            jsonResponse.setError(false);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/createPropiedads", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse> createPropiedad(HttpServletRequest request, @RequestBody List<Propiedad> propiedads){
        String authHeader = request.getHeader("Authorization");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            for (Propiedad p : propiedads){
                propiedadDao.save(p);
            }
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("Propiedad creado Correctamente");
            jsonResponse.setError(false);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }





    @RequestMapping(value = "/guardarFavorito", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse> guardarFavorito(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        long propiedad_id =  Long.parseLong(request.getParameter("propiedad_id"));
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            Optional propiedad = null ;
            propiedad = propiedadDao.findById(propiedad_id);
            if(propiedad.isPresent()){
                List<User> users = new ArrayList<User>();
                users.add(user);
                Propiedad p = (Propiedad) propiedad.get();
                p.setUsers(users);
                propiedadDao.save(p);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setMensaje("Propiedad guardada Correctamente");
                jsonResponse.setError(false);
                return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
            }
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/getMaxValor", method= RequestMethod.GET)
    public Double getMaxValor(HttpServletRequest request){
        propiedadDao.getMaxValor();
        return propiedadDao.getMaxValor();
    }


    @RequestMapping(value = "/getNotificationByUser", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getNotificationByUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            Iterable<Notificacion> notificacionsI = notificaionDao.findAll();
            List<Notificacion> notificacions =  new ArrayList<Notificacion>();
            for(Notificacion n : notificacionsI){
                notificacions.add(n);
            }
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(notificacions);

            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/vender_property", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse>  vender_property(HttpServletRequest request){
        User user = null;
        user = userDao.findByToken("gblgwduauwthuvbmmmslldazictvkhjnokxntcdz");
        if(user != null){
            /*Iterable<Notificacion> notificacionsI = notificaionDao.findAll();
            List<Notificacion> notificacions =  new ArrayList<Notificacion>();
            for(Notificacion n : notificacionsI){
                notificacions.add(n);



            }*/
            Optional notificacion_op  = vendedorDao.findById(Long.valueOf(1));
            Vendedor notificacion = (Vendedor) notificacion_op.get();

            Iterable<Propiedad> propiedads = propiedadDao.findAll();
            List<Propiedad> propiedadsd = new ArrayList<Propiedad>();
            for (Propiedad p : propiedads){
                List<Vendedor> notificacions =  new ArrayList<Vendedor>();
                notificacions.add(notificacion);
                p.setVendedors(notificacions);
                propiedadDao.save(p);
                //propiedadsd.add(p);

            }
            //n.setPropiedads(propiedadsd);
            //notificaionDao.save(n);

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedadsd);

            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

    }


    @RequestMapping(value = "/getDirectionByProperty", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse>  getDirectionByProperty(HttpServletRequest request){
        List<String>comuna = propiedadDao.findComuna();

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(false);
        jsonResponse.setData(comuna);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

    }


    @RequestMapping(value = "/getPropertyBuFilter", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse>  getPropertyBuFilter(HttpServletRequest request , @RequestBody Filter filter){

        filterDao.save(filter);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("data enviados");

        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

       
    }


    @RequestMapping(value = "/getAlertasByUser", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getAlertasByUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            Iterable<Filter> notificacionsI = filterDao.findAll();
            List<Filter> notificacions =  new ArrayList<Filter>();
            for(Filter n : notificacionsI){
                notificacions.add(n);
            }
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(notificacions);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/prueba", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse>  prueba(HttpServletRequest request ){

        String description = request.getParameter("description");

       /*List<Propiedad> propiedads = propiedadDao.findFilter(description,"");
       System.out.println(description);
       for (Propiedad p : propiedads){
           System.out.println(p.getDescription());
       }*/


       return  null;
    }

    @RequestMapping(value = "/filter", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse>   filter(HttpServletRequest request , @RequestBody Filter filter){

        if(filter.getMax() == 0.0){
            filter.setMax(1000000000000.0);
        }

        System.out.println(1+" "+filter.getDescription());
        System.out.println(2+" "+filter.getMax());
        System.out.println(3+" "+filter.getMin());
        System.out.println(4+" "+filter.getComuna());

        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "direccion"));
        Pageable pageable = new PageRequest(0, 2, sort);

        if(filter.getComuna() == 0){
            System.out.println(4+" tipo region "+filter.getRegion());
            Optional region_op = regionDao.findById(filter.getRegion());
            Region region =  (Region) region_op.get();
            List<Propiedad> p =   propiedadDao.findFilterOnlyRegion(filter.getDescription(),filter.getObjetivo(),filter.getMin(),filter.getMax(),region,pageable);
            /*System.out.println(propiedads.size());
            for (Propiedad propiedad : propiedads){
                System.out.println(propiedad.getDireccion());
            }*/

            List<ExtraJson> propiedads = new ArrayList<ExtraJson>();
            for(Propiedad pro : p){
                //pro.setVendedorJson(pro.getVendedor());
                ExtraJson ex = new ExtraJson();
                //System.out.println(pro.getVendedor().getNombre());
                ex.setData(pro);
                Vendedor v = new Vendedor() ;

                ex.setExtra(pro.getVendedors().get(0));

                propiedads.add(ex);
            }

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedads);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }else{
            System.out.println(4+" tipo comuna "+filter.getComuna());
            List<Propiedad> p =   propiedadDao.findFilter(filter.getDescription(),filter.getObjetivo(),filter.getMin(),filter.getMax(),filter.getComuna(),pageable);
            List<ExtraJson> propiedads = new ArrayList<ExtraJson>();
            for(Propiedad pro : p){
                //pro.setVendedorJson(pro.getVendedor());
                ExtraJson ex = new ExtraJson();
                //System.out.println(pro.getVendedor().getNombre());
                ex.setData(pro);
                Vendedor v = new Vendedor() ;

                ex.setExtra(pro.getVendedors().get(0));

                propiedads.add(ex);
            }

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedads);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }


        /*JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Errror de filtro");
        jsonResponse.setError(false);

        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);*/
    }

    @RequestMapping(value = "/user", method= RequestMethod.GET)
    public String   user(HttpServletRequest request){

       /* User user = null;
         user = userDao.findByToken("ufovqqbbsfjyzmcibrajcblhpijdekvnlbgteggf");
        if(user != null){

            Optional notificacion_op  = notificaionDao.findById(Long.valueOf(1));
            Notificacion notificacion = (Notificacion) notificacion_op.get();

            Iterable<Propiedad> propiedads = propiedadDao.findAll();
            List<Propiedad> propiedadsd = new ArrayList<Propiedad>();
            for (Propiedad p : propiedads){
                List<Notificacion> notificacions =  new ArrayList<Notificacion>();
                notificacions.add(notificacion);
                p.setNotificacions(notificacions);
                propiedadDao.save(p);
                //propiedadsd.add(p);

            }
            //n.setPropiedads(propiedadsd);
            //notificaionDao.save(n);

            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("data enviados");
            jsonResponse.setData(propiedadsd);

            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }cms
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);*/


        User user = new User();
        String password = "123456";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        user.setEmail("francoqueto@gmail.com");
        user.setClave(bcryptHashString);
        user.setToken("");
        user.setDevice("");
        user.setDevice_token("");
        userDao.save(user);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        System.out.println(result.verified);
        return String.valueOf(result.verified);
    }


    public String token(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 40;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return  generatedString;
    }


    @CrossOrigin
    @RequestMapping(value = "/getSectores", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> get_barrio(HttpServletRequest request){
        List<String> optionl = propiedadDao.findSector();



        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("data enviados");
        jsonResponse.setData(optionl);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);

    }

    /*

        Api Admin inicia aqui


     */


    @CrossOrigin
    @RequestMapping(value = "/getVendedores", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getVendedores(HttpServletRequest request){
        Iterable<Vendedor> vendedor = vendedorDao.findAll();

        List<Vendedor>vendedors = new ArrayList<Vendedor>();

        for(Vendedor v : vendedor){
            vendedors.add(v);
        }

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("data enviados");
        jsonResponse.setData(vendedors);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/getComunas", method= RequestMethod.GET)
    public ResponseEntity<JsonResponse> getComunas(HttpServletRequest request){
        Iterable<Comuna> vendedor = comunaDao.findAll();

        List<Comuna>vendedors = new ArrayList<Comuna>();

        for(Comuna v : vendedor){
            vendedors.add(v);
        }

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("data enviados");
        jsonResponse.setData(vendedors);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/createVendedor", method= RequestMethod.POST)
    public ResponseEntity<JsonResponse> getAllRegion(HttpServletRequest request, @RequestBody Vendedor vendedor){
        String authHeader = request.getHeader("Authorization");
        User user = null;
        user = userDao.findByToken(authHeader);
        if(user != null){
            vendedorDao.save(vendedor);
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setMensaje("Vendedor creado Correctamente");
            jsonResponse.setError(false);
            return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMensaje("Token invalido");
        jsonResponse.setError(true);
        return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

}
