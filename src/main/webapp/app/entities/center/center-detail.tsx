import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './center.reducer';

export const CenterDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const centerEntity = useAppSelector(state => state.center.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="centerDetailsHeading">Center</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{centerEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{centerEntity.name}</dd>
          <dt>
            <span id="location">Location</span>
          </dt>
          <dd>{centerEntity.location}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{centerEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/center" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/center/${centerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CenterDetail;
