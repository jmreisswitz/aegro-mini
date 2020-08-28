import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Farm} from "../models/farm";
import {Observable} from "rxjs";
import {ErrorHandlingService} from "./error-handling.service";
import {catchError} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class FarmService {
  private farmUrl: string = 'http://localhost:8081/aegro_mini/farm/';

  constructor(
    private http: HttpClient,
    private errorHandler: ErrorHandlingService
  ) { }

  getFarms(): Observable<Farm[]> {
    return this.http.get<Farm[]>(this.farmUrl + 'farms')
      .pipe(catchError(this.errorHandler.handleError<Farm[]>('getFarms', [])));
  }

  getFarm(id: string): Observable<Farm> {
    return this.http.get<Farm>(this.farmUrl + id)
      .pipe(catchError(this.errorHandler.handleError<Farm>('getFarm')));
  }

  addFarm(farm: Farm): Observable<Farm> {
    return this.http.post<Farm>(this.farmUrl, farm)
      .pipe(catchError(this.errorHandler.handleError<Farm>('addFarm')));
  }

  deleteFarm(id: string): Observable<Farm> {
    return this.http.delete<Farm>(this.farmUrl + id)
      .pipe(catchError(this.errorHandler.handleError<Farm>('deleteFarm')));
  }
}
