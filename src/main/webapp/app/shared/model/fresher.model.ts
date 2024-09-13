import { ICenter } from 'app/shared/model/center.model';
import { IProject } from 'app/shared/model/project.model';

export interface IFresher {
  id?: number;
  name?: string;
  email?: string;
  programmingLanguage?: string;
  assignment1Score?: number | null;
  assignment2Score?: number | null;
  assignment3Score?: number | null;
  finalScore?: number | null;
  center?: ICenter | null;
  projects?: IProject[] | null;
}

export const defaultValue: Readonly<IFresher> = {};
