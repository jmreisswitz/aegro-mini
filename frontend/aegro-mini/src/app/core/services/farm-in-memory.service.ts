import { Injectable } from '@angular/core';
import {Farm} from "../models/farm";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FarmInMemoryService {
  private farms: Farm[];

  constructor() {
    this.farms = [
      { id: 'abcd', name: 'Farm 1', fields: []},
      { id: 'abcde', name: 'Farm 2', fields: []},
      { id: '123456', name: 'W8 fields', fields: []}
    ]
  }

  getFarms(): Farm[] {
    return this.farms;
  }

  getFarm(id: string): Observable<Farm> {
    return of(this.farms.find(farm => farm.id === id));
  }
}
