import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CalculateComponent} from "./calculate/calculate.component";

const routes: Routes = [
  {path: '', component: CalculateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
