package io.feeflow.controller;

/**
 * POST /api/v1/students
 * POST /api/v1/students/bulk-upload
 * students.xlsx
 * Upload Excel
 *       ↓
 * Parse rows
 *       ↓
 * Create students
 *       ↓
 * Return success report
 *
 * Total rows: 100
 * Created: 96
 * Failed: 4
 *
 * GET /api/v1/students
 * GET /api/v1/students/{id}
 * PUT /api/v1/students/{id}
 * DELETE /api/v1/students/{id}
 */
public class StudentController {
}
