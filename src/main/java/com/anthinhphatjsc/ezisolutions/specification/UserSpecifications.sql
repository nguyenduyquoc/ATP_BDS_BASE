case IN -> (root, query, criteriaBuilder) -> {
    if (input.getField().equals("roles.id")) {
    // Truy cập vào tập hợp roles, sau đó truy cập vào id của mỗi RoleEntity
    Join<UserEntity, RoleEntity> rolesJoin = root.join("roles");
    return criteriaBuilder.in(rolesJoin.get("id"))
           .value(castToRequiredType(Long.class, input.getValues()));
    } else
    if (StringUtil.isNotEmpty(input.getValue())) {
        return criteriaBuilder.in(root.get(input.getField()))
            .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
    } else {
        // Xử lý trường hợp input.getValues() không null
        CriteriaBuilder.In<Object> inClause = criteriaBuilder.in(root.get(input.getField()));
        for (String value : input.getValues()) {
            inClause.value(castToRequiredType(root.get(input.getField()).getJavaType(), value));
        }
        return inClause;
    }
};