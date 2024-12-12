import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  private apiUrl = 'http://localhost:8080/api/recipes';

  constructor(private http: HttpClient) {}

  createRecipe(recipe: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, recipe);
  }

  getAllRecipes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  searchRecipes(searchTerm: string = '', mealTypes: string[] = []): Observable<any[]> {
    let params = new HttpParams();

    if (searchTerm) {
      params = params.set('name', searchTerm.trim());
    }

    if (mealTypes.length > 0) {
      params = params.set('mealTypes', mealTypes.join(',')); // Send as a comma-separated list
    }

    return this.http.get<any[]>(`${this.apiUrl}/search`, { params });
  }

  deleteRecipe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
