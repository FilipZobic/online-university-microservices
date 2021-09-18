import { createStore, applyMiddleware, compose } from "redux";
import createSagaMiddleware from "@redux-saga/core";
import reducer from "./reducers/";
import authSaga from "./sagas/auth";
import userSaga from "./sagas/users";

const sagaMiddleware = createSagaMiddleware();

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const composeEnhancers = (window as any).__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(reducer, composeEnhancers(applyMiddleware(sagaMiddleware)));

sagaMiddleware.run(authSaga)
sagaMiddleware.run(userSaga)

export default store;