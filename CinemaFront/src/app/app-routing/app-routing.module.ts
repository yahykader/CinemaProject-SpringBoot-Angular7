import { NgModule } from '@angular/core';
import{Routes,RouterModule} from '@angular/router';
import { CommonModule } from '@angular/common';
import { CinemaComponent } from '../components/cinema/cinema.component';

const routes:Routes = [
    {path:"cinema",component:CinemaComponent}
];
@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes),
    CommonModule
  ],
  exports :[RouterModule]
})
export class AppRoutingModule { }
