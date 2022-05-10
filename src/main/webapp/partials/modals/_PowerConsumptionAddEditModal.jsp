
<div class="modal fade" id="powerConsumptionModal" tabindex="-1" role="dialog" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="powerConsumptionModalTitle"></h3>

                
                        <button
          type="button"
          class="close modalCloseBtn"
          aria-label="Close"
          id="powerConsumptionModalCloseBtn"
        >
          <span aria-hidden="true">&times;</span>
        </button>
            </div>
            <div class="modal-body">
                <div class="custom-form grid _1">
                    <div>
                        <label class="asterix">User</label>
                        <select id="pcUser">
                        	<option value="0">Please select a user</option>
                        </select>
                    </div>
                       <div>
                        <label class="asterix">Mobile Number</label>
                        <input type="tel" id="pcMobileNumber"/>
                    </div>
                       <div>
                        <label class="asterix">Units</label>
                        <input type="number" id="pcUnits"/>
                    </div>
                       <div>
                        <label class="asterix">Bill Date</label>
                        <input class="datetimepicker date" type="text" id="pcBillDate"/>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                    <button class="save" id="savePC">Save</button>
            </div>
        </div>
    </div>
</div>


