import {Injectable} from '@angular/core';
import {environment} from "../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CalculateRequest} from "./model/calculate-request";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  readonly calculateUrl = environment.restUrl + "/api/calculate";

  constructor(private http: HttpClient) {
  }

  public calculate(request: CalculateRequest): Observable<number> {
    return this.http.post<number>(this.calculateUrl, request);
  }
}
