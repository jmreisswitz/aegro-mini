import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Farm} from "../models/farm";
import {Observable} from "rxjs";

// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'application/json'})
// };

@Injectable({
  providedIn: 'root'
})
export class FarmService {
  private farmUrl: string = 'http://localhost:8081/aegro_mini/farm/';

  constructor(private http: HttpClient) { }

  getFarms(): Observable<Farm[]> {
    return this.http.get<Farm[]>(this.farmUrl + 'farms');
  }

  getFarm(id: string): Observable<Farm> {
    return this.http.get<Farm>(this.farmUrl + id);
  }
}
