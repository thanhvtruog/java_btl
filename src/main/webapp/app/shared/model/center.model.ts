import { IFresher } from 'app/shared/model/fresher.model';
import { IProject } from 'app/shared/model/project.model';

export interface ICenter {
  id?: number;
  name?: string;
  location?: string | null;
  description?: string | null;
  freshers?: IFresher[] | null;
  projects?: IProject[] | null;
}

export const defaultValue: Readonly<ICenter> = {};
