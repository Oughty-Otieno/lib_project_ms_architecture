<template>
  <div>
    <h2 id="page-heading" data-cy="BorrowingHeading">
      <span id="borrowing-heading">Borrowings</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'BorrowingCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-borrowing"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Borrowing </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && borrowings && borrowings.length === 0">
      <span>No borrowings found</span>
    </div>
    <div class="table-responsive" v-if="borrowings && borrowings.length > 0">
      <table class="table table-striped" aria-describedby="borrowings">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('date_borrowed')">
              <span>Date Borrowed</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'date_borrowed'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('due_date')">
              <span>Due Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'due_date'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('return_date')">
              <span>Return Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'return_date'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user_id')">
              <span>User Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user_id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('book.title')">
              <span>Book</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'book.title'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fine')">
                          <span>Fine Accrued</span>
                          <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user_id'"></jhi-sort-indicator>
                        </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="borrowing in borrowings" :key="borrowing.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BorrowingView', params: { borrowingId: borrowing.id } }">{{ borrowing.id }}</router-link>
            </td>
            <td>{{ borrowing.date_borrowed }}</td>
            <td>{{ borrowing.due_date }}</td>
            <td>{{ borrowing.return_date }}</td>
            <td>{{ borrowing.user_id }}</td>
            <td>
              <div v-if="borrowing.book">
                <router-link :to="{ name: 'BookView', params: { bookId: borrowing.book.id } }">{{ borrowing.book.title }}</router-link>
              </div>
            </td>
            <td>{{ borrowing.fine_accrued}}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BorrowingView', params: { borrowingId: borrowing.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BorrowingEdit', params: { borrowingId: borrowing.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(borrowing)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="libgatewayApp.libprojectBorrowing.delete.question" data-cy="borrowingDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-borrowing-heading">Are you sure you want to delete this Borrowing?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-borrowing"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeBorrowing()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="borrowings && borrowings.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./borrowing.component.ts"></script>
