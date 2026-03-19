package io.feeflow.controller;

/**
 * POST /api/v1/fee-structures
 * GET /api/v1/fee-structures
 * GET /api/v1/fee-structures/{id}
 *
 * {
 * "batchId":1,
 * "totalFee":60000,
 * "installments":[
 * {"number":1,"amount":20000,"offsetDays":0},
 * {"number":2,"amount":20000,"offsetDays":30},
 * {"number":3,"amount":20000,"offsetDays":60}
 * ]
 * }
 */
public class FeeStructureController {
}
