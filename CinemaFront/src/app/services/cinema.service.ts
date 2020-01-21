import { Injectable, OnInit } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CinemaService{
   private host :String ="http://localhost:8080";
  constructor(private http: HttpClient) { }
  
  public  getVilles(){
    return this.http.get(this.host+"/villes");
  }

  public getCinemas(v){
    return this.http.get(v._links.cinemas.href);
  }
  public getSalles(c){
    return this.http.get(c._links.salles.href);
  }
}
