import { Component, OnInit } from '@angular/core';
import { CinemaService } from 'src/app/services/cinema.service';

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {
  public villes :any;
  public cinemas :any;
  public salles :any;
  public currentVille:any;
  public currentCinema:any;
  constructor(private cinemaService:CinemaService) { }

  ngOnInit() {
    this.getVilles();
  }

  public getVilles(){
    this.cinemaService.getVilles().subscribe(data=>{
            this.villes=data;
    },err=>{
      console.log(err);
    });
  }
  public onGetCinemas(v:any){
    this.currentVille=v;
    this.cinemaService.getCinemas(v).subscribe(data=>{
      this.cinemas=data;
    },err=>{
    console.log(err);
  });
  }
  public onGetSalles(c){
    this.currentCinema=c;
    this.cinemaService.getSalles(c).subscribe(data=>{
      this.salles=data;
    },err=>{
    console.log(err);
  }); 
  }

}
