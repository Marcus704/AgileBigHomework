import React from "react";
import {
  render,
  fireEvent,
  act,
  wait,
  getByTestId,
  getAllByTestId,
} from "@testing-library/react";
import App from "./App";
import * as TodoApi from "./api/TodoApi";

describe("<App>", () => {
  const item = { id: 1, content: "First Item", createAt: "2020/04/16" };
  const updateItem = { id: 1, content: "Update Item", createAt: "2020/04/16" };
  const addedItem = { id: 2, content: "Second Item", createAt: "2020/04/16" };

  beforeEach(() => {
    jest
      .spyOn(TodoApi, "getTodos")
      .mockImplementation(() => Promise.resolve([item]));
  });

  test("should display todo items correctly", async () => {
    await act(async () => {
      render(<App />);
    });

    expect(getByTestId(document.body, "task-item")).toHaveTextContent(
      "First Item"
    );
  });

});