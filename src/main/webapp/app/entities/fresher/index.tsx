import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Fresher from './fresher';
import FresherDetail from './fresher-detail';
import FresherUpdate from './fresher-update';
import FresherDeleteDialog from './fresher-delete-dialog';

const FresherRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Fresher />} />
    <Route path="new" element={<FresherUpdate />} />
    <Route path=":id">
      <Route index element={<FresherDetail />} />
      <Route path="edit" element={<FresherUpdate />} />
      <Route path="delete" element={<FresherDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FresherRoutes;
