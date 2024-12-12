import { Routes } from '@angular/router';
import { RecipesComponent } from './pages/recipes/recipes.component';
import { CreateRecipeComponent } from './pages/create-recipe/create-recipe.component';

export const routes: Routes = [
  { path: '', redirectTo: '/recipes', pathMatch: 'full' },
  { path: 'recipes', component: RecipesComponent },
  { path: 'create-recipe', component: CreateRecipeComponent }
];
