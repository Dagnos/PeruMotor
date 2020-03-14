package com.example.prueba.Interface;

import com.example.prueba.Models.CambioFecha;
import com.example.prueba.Models.Factura;
import com.example.prueba.Models.FormaPago;
import com.example.prueba.Models.Moneda;
import com.example.prueba.Models.NumeroOT;
import com.example.prueba.Models.OrdenTrabajo;
import com.example.prueba.Models.Periodo;
import com.example.prueba.Models.PostOrdenTrabajo;
import com.example.prueba.Models.PostVehiculo;
import com.example.prueba.Models.ResponseClass;
import com.example.prueba.Models.Sucursales;
import com.example.prueba.Models.TipoOrden;
import com.example.prueba.Models.TipoTrabajo;
import com.example.prueba.Models.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIPerumotor {

   //orden de trabajo consultas
   @GET("ordentrabajo/")
   Call<List<OrdenTrabajo>> getOrdenTrabajo();

   //orden de trabajo busqueda
   @GET("ordentrabajo/busqueda/{id}")
   Call<List<OrdenTrabajo>> getOrdenTrabajo2(@Path("id") String IDVEHICULO);

   //vehiculos consulta
   @GET("vehiculo/busqueda/{id}")
   Call<List<Vehiculo>> getVehiculo(@Path("id")String PLACA);

   //tipo orden
   @GET("tipoorden/")
   Call<ArrayList<TipoOrden>> getTipoOrden();

   //moneda
   @GET("moneda/")
   Call<ArrayList<Moneda>> getMoneda();

   //tipotrabajo
   @GET("tipotrabajo/")
   Call<ArrayList<TipoTrabajo>> getTipoTrabajo();

   //sucursales
   @GET("sucursales/")
   Call<ArrayList<Sucursales>> getSucursales();

   //formapago
   @GET("formapago/")
   Call<ArrayList<FormaPago>> getFormaPago();


   //Factura
   @GET("factura/busqueda/{id}")
   Call<List<Factura>> getFactura(@Path("id")String IDVEHICULO);

   //Número de OT
   @GET("numeroot/")
   Call<List<NumeroOT>> getNumeroOT();

   //Número de OT
   @GET("cambiofecha/")
   Call<List<CambioFecha>> getCambioFecha();

   //Número de OT
   @GET("periodo/")
   Call<List<Periodo>> getPeriodo();

   //postOrdenTrabajo
   @FormUrlEncoded
   @POST("ordentrabajo/postordentrabajo")
   Call<PostOrdenTrabajo> createOrdenTrabajo(
           @Field("periodo") String periodo,
           @Field("idsucursal") String idsucursal,
           @Field("fechainicio") String fechainicio,
           @Field("idmoneda") String idmoneda,
           @Field("idresponsable") String idresponsable,
           @Field("serie") String serie,
           @Field("tcambio") String tcambio,
           @Field("idclieprov") String idclieprov,
           @Field("idvehiculo") String idvehiculo,
           @Field("kilometraje") String kilometraje,
           @Field("idfpago") String idfpago,
           @Field("idactividad") String idactividad,
           @Field("garantia") String garantia
   );


   //postVehiculo
   @FormUrlEncoded
   @POST("vehiculo/postvehiculo")
   Call<PostVehiculo> createVehiculo(
           @Field("IDVEHICULO") String IDVEHICULO,
           @Field("IDCLIEPROV") String IDCLIEPROV,
           @Field("NROMOTOR") String NROMOTOR,
           @Field("NROCHASIS") String NROCHASIS,
           @Field("IDCOLOR") String IDCOLOR,
           @Field("IDMARCA") String IDMARCA,
           @Field("IDMODELOVEHICULO") String IDMODELOVEHICULO,
           @Field("ANIO") String ANIO,
           @Field("VIN") String VIN,
           @Field("aniomodelo") String aniomodelo
   );


   @POST("image/upload")
   @FormUrlEncoded
   Call<ResponseClass> UploadImage(
           @Field("num") String num,
           @Field("image") String image
   );

   @POST("image/upload2")
   @FormUrlEncoded
   Call<ResponseClass> UploadImage2(
           @Field("num") String num,
           @Field("image") String image
   );

   @POST("image/upload3")
   @FormUrlEncoded
   Call<ResponseClass> UploadImage3(
           @Field("num") String num,
           @Field("image") String image
   );

   @POST("image/upload4")
   @FormUrlEncoded
   Call<ResponseClass> UploadImage4(
           @Field("num") String num,
           @Field("image") String image
   );

   @POST("image/upload5")
   @FormUrlEncoded
   Call<ResponseClass> UploadImage5(
           @Field("num") String num,
           @Field("image") String image
   );

   @POST("image/upload6")
   @FormUrlEncoded
   Call<ResponseClass> UploadImage6(
           @Field("num") String num,
           @Field("image") String image
   );

   @POST("image/upload7")
   @FormUrlEncoded
   Call<ResponseClass> UploadImage7(
           @Field("num") String num,
           @Field("image") String image
   );

  @POST("image/uploadfirma")
  @FormUrlEncoded
  Call<ResponseClass> UploadFirma(
          @Field("num") String num,
          @Field("image") String image
  );
}


