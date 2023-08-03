import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IntroComponent } from './intro/intro.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RecordComponent } from './record/record.component';
import { RecordListComponent } from './record-list/record-list.component';

const routes: Routes = [
  {path:'',
    redirectTo:"/home",
    pathMatch:'full'},
  { path: 'home', component: IntroComponent },
  {path:'record',component:RecordComponent},
  {path:"record-list",component:RecordListComponent},
  { path: '**', component: PageNotFoundComponent  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
